package com.sflpro.cafe.domain.create;

public class TableCreationRequest {

    private Integer number;
    private Long waiterId;

    public TableCreationRequest() {
        this(null, null);
    }

    public TableCreationRequest(
            final Integer number,
            final Long waiterId
    ) {
        this.number = number;
        this.waiterId = waiterId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Long getWaiterId() {
        return waiterId;
    }

    public void setWaiterId(Long waiterId) {
        this.waiterId = waiterId;
    }
}
