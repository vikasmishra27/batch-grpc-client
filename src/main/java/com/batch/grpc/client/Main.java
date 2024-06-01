package com.batch.grpc.client;

import com.batch.grpc.client.employee.BookRequest;
import com.batch.grpc.client.employee.BookResponse;
import com.batch.grpc.client.employee.BookServiceGrpc;
import com.batch.grpc.client.constants.Type;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class Main {


    public static void main(String[] args) {

        String host=args[0];
        Integer port= Integer.valueOf(args[1]);
        String bookId=args[2];

        if (args.length < 3){
                System.err.println("Less argument passed");
                System.exit(1);
        }


        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        BookServiceGrpc.BookServiceBlockingStub blockingStub = BookServiceGrpc.newBlockingStub(channel);
        //String bookId = "123"; // Example book ID
        BookRequest request = BookRequest.newBuilder().setBookId(bookId).build();
        BookResponse response = blockingStub.getBook(request);
        System.out.println("Book ID: " + response.getBookId());
        System.out.println("Name: " + response.getName());
        System.out.println("Type: " + response.getType());

    }
}
