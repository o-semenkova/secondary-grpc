package com.grpc;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class AppendMessageServiceImpl extends com.grpc.AppendMessageServiceGrpc.AppendMessageServiceImplBase {

  private HashMap<Long, String> messages = new HashMap<>();

  @Override
  public void append(com.grpc.LogMessage request,
                                                     StreamObserver<com.grpc.LogMessageAck> responseObserver) {
    messages.put(request.getId(), request.getText());

    com.grpc.LogMessageAck ack = com.grpc.LogMessageAck.newBuilder()
                                                                     .setId(request.getId())
                                                                     .setStatus("OK")
                                                                     .build();
    responseObserver.onNext(ack);
    responseObserver.onCompleted();
  }

  private String convertWithStream(Map<Long, String> map) {
    String mapAsString = map.keySet().stream()
                            .map(key -> key + "=" + map.get(key))
                            .collect(Collectors.joining(", ", "{", "}"));
    return mapAsString;
  }

  public String getAllMessages() {
    return convertWithStream(messages);
  }
}
