package com.prodinv.dtos;

import java.time.LocalDateTime;

public class ErrorDetail
{
    private LocalDateTime timestamp;
    private String message;
    private Integer status;
    private String detail;

    public ErrorDetail() {}

    public ErrorDetail(String message, Integer status, String detail)
    {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
        this.detail = detail;
    }

    private ErrorDetail(LocalDateTime timestamp, String message, Integer status, String detail)
    {
        this.timestamp = timestamp;
        this.message = message;
        this.status = status;
        this.detail = detail;
    }

    public LocalDateTime getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp)
    {
        this.timestamp = timestamp;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public String getDetail()
    {
        return detail;
    }

    public void setDetail(String detail)
    {
        this.detail = detail;
    }
}
