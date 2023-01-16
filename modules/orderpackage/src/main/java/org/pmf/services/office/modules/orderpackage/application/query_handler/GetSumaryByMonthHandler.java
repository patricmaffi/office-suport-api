package org.pmf.services.office.modules.orderpackage.application.query_handler;

import org.pmf.services.office.modules.orderpackage.application.query.GetSumaryByMonth;
import org.pmf.services.office.modules.orderpackage.domain.OrderPackage;
import org.pmf.services.office.modules.orderpackage.domain.OrderPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Component
public class GetSumaryByMonthHandler {

    OrderPackageRepository repository;

    @Autowired
    public GetSumaryByMonthHandler(OrderPackageRepository repository) {
        this.repository = repository;
    }

    public List<OrderPackage> handle(GetSumaryByMonth envelope) {
        Date month = envelope.getMonth();
        Calendar c = Calendar.getInstance();
        c.setTime(month);
        LocalDateTime start = toLocalDateTime(c);
        c.add(Calendar.DAY_OF_MONTH, 1);
        LocalDateTime end = toLocalDateTime(c);
        var list = repository.findByMonth(start, end, envelope.getPageable());
        return list;
    }
    private LocalDateTime parse(Date month, int day)
    {
        Calendar start = Calendar.getInstance();   // this takes current date
        start.setTime(month);
        start.set(Calendar.DAY_OF_MONTH, day);
        return toLocalDateTime(start);
    }

    private LocalDateTime toLocalDateTime(Calendar day)
    {
        TimeZone tz = day.getTimeZone();
        ZoneId zoneId = tz.toZoneId();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(day.toInstant(), zoneId);
        return localDateTime;
    }
}
