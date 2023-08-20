package com.hedza06.grpc;

import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.StatusException;
import jakarta.persistence.EntityNotFoundException;
import net.devh.boot.grpc.server.advice.GrpcAdvice;

@GrpcAdvice
public class GrpcExceptionHandler {

    @net.devh.boot.grpc.server.advice.GrpcExceptionHandler(EntityNotFoundException.class)
    public StatusException handleEntityNotFoundException(EntityNotFoundException ex) {
        var metadata = new Metadata();
        metadata.put(Metadata.Key.of("message", Metadata.ASCII_STRING_MARSHALLER), ex.getMessage());
        metadata.put(Metadata.Key.of("errorCode", Metadata.ASCII_STRING_MARSHALLER), "entity-not-found");

        var status = Status.NOT_FOUND.withDescription("Entity not found!").withCause(ex);
        return status.asException(metadata);
    }
}
