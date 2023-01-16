package org.pmf.services.office.modules.financial_request.domain

import org.hibernate.HibernateException
import org.hibernate.engine.spi.SharedSessionContractImplementor
import org.hibernate.type.IntegerType
import org.hibernate.type.LongType
import org.hibernate.type.StringType
import org.hibernate.type.Type
import org.hibernate.usertype.CompositeUserType
import org.pmf.services.office.core.value_object.Money
import java.io.Serializable
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import java.util.*

open class MoneyType : CompositeUserType {
    override fun equals(x: Any?, y: Any?): Boolean {
        return Objects.equals(x, y);
    }

    override fun hashCode(x: Any?): Int {
        return Objects.hashCode(x);
    }

    override fun getPropertyNames(): Array<String> {
        return arrayOf("amount", "currency")
    }

    override fun getPropertyTypes(): Array<out Type> {
        return arrayOf(IntegerType.INSTANCE, StringType.INSTANCE)
    }

    override fun getPropertyValue(component: Any?, propertyIndex: Int): Any? {
        if (component == null) {
            return null
        }
        val money = component as Money
        return when (propertyIndex) {
            0 -> {
                money.amount
            }
            1 -> {
                money.currency
            }
            else -> {
                throw HibernateException("Invalid property index [$propertyIndex]")
            }
        }
    }

    @Throws(HibernateException::class)
    override fun setPropertyValue(component: Any?, propertyIndex: Int, value: Any?) {
    }

    override fun returnedClass(): Class<*> {
        return Money::class.java
    }

    @Throws(SQLException::class)
    override fun nullSafeGet(
        rs: ResultSet?,
        names: Array<out String>?,
        session: SharedSessionContractImplementor?,
        owner: Any?
    ): Any {
        assert(names?.size == 2)

        val amount: Int? = IntegerType.INSTANCE.get(rs, names!![0], session) as Int?
        val currency: String? = StringType.INSTANCE.get(rs, names[1], session) as String?

        return (if (amount == null && currency == null) null else Money(
            currency,
            amount!!
        ))!!
    }

    @Throws(SQLException::class)
    override fun nullSafeSet(
        st: PreparedStatement?,
        value: Any?,
        index: Int,
        session: SharedSessionContractImplementor?
    ) {
        if (value == null) {
            LongType.INSTANCE.set(st, null, index, session)
            StringType.INSTANCE.set(st, null, index + 1, session)
        } else {
            val money = value as Money
            IntegerType.INSTANCE.set(st, money.amount, index, session)
            StringType.INSTANCE.set(st, money.currency, index + 1, session)
        }
    }

    override fun deepCopy(value: Any?): Any? {
        val received = value as Money
        return Money(
            received.currency,
            received.amount
        )
    }

    override fun isMutable(): Boolean = false

    override fun disassemble(value: Any?, session: SharedSessionContractImplementor?): Serializable {
        return deepCopy(value) as BitSet;
    }

    override fun assemble(cached: Serializable?, session: SharedSessionContractImplementor?, owner: Any?): Any? {
        return deepCopy(cached) as BitSet;
    }

    override fun replace(original: Any?, target: Any?, session: SharedSessionContractImplementor?, owner: Any?): Any? {
        return deepCopy(original);
    }
}
