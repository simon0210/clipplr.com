package com.clipplr.platform.exception.transaction;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by simon on 9/21/15.
 */
public class NotEnoughRubyException extends IllegalStateException {

    private static final long serialVersionUID = -1537781767996440884L;
    private static final DecimalFormat decimalFormat = new DecimalFormat("#0.##");

    private Long accountId = null;
    private BigDecimal balance = null;
    private BigDecimal demand = null;

    public NotEnoughRubyException(long accountId, BigDecimal balance, BigDecimal demand) {
        this.accountId = accountId;
        this.balance = balance;
        this.demand = demand;
    }

    public Long getAccountId() {
        return accountId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public String getMessage() {
        return (accountId == null)
                ? "BALANCE_NOT_ENOUGH"
                : String.format("Account doesn't have enough ruby balance. %s Demanded, but there is %s only.", decimalFormat.format(demand), decimalFormat.format(balance));
    }

}
