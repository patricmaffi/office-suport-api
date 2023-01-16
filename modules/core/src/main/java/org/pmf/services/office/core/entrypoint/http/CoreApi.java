package org.pmf.services.office.core.entrypoint.http;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/core")
public class CoreApi {
    @GetMapping("")
    public String index() {
        return "Core module";
    }
}
