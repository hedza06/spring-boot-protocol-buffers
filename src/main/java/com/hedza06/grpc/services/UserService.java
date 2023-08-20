package com.hedza06.grpc.services;

import com.google.protobuf.Empty;
import com.hedza06.grpc.CreateUserRequest;
import com.hedza06.grpc.CreateUserResponse;
import com.hedza06.grpc.GetAllUsersResponse;
import com.hedza06.grpc.GetUserDetailsRequest;
import com.hedza06.grpc.GetUserDetailsResponse;
import com.hedza06.grpc.UserServiceGrpc.UserServiceImplBase;
import com.hedza06.grpc.entities.User;
import com.hedza06.grpc.repositories.UserRepository;
import io.grpc.stub.StreamObserver;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.List;

@Slf4j
@GrpcService
@RequiredArgsConstructor
public class UserService extends UserServiceImplBase {

    private final UserRepository userRepository;

    @Override
    public void getDetails(GetUserDetailsRequest request, StreamObserver<GetUserDetailsResponse> responseObserver) {
        log.info("Request for getting user details for user with id {}", request.getId());

        User user = userRepository
                .findById(request.getId())
                .orElseThrow(() -> new EntityNotFoundException("User with id " + request.getId() + " not found"));

        var response = GetUserDetailsResponse.newBuilder()
                .setId(user.getId())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setAge(user.getAge())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void create(CreateUserRequest request, StreamObserver<CreateUserResponse> responseObserver) {
        log.info("Request for creating new user...");

        var user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setAge(request.getAge());
        userRepository.save(user);

        var response = CreateUserResponse.newBuilder().setId(user.getId()).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getAll(Empty request, StreamObserver<GetAllUsersResponse> responseObserver) {
        log.info("Request for streaming all users...");

        List<User> users = userRepository.findAll();
        for (User user : users) {
            try {
                log.info("Streaming the user to client {}", user);
                Thread.sleep(3000);
                var response = GetAllUsersResponse.newBuilder()
                        .setId(user.getId())
                        .setFirstName(user.getFirstName())
                        .setLastName(user.getLastName())
                        .setEmail(user.getEmail())
                        .setAge(user.getAge())
                        .build();

                responseObserver.onNext(response);
            } catch (InterruptedException e) {
                log.error("Interrupted execution while streaming users. Message: {}", e.getMessage());
                Thread.currentThread().interrupt();
            }
            responseObserver.onCompleted();
        }
    }
}
