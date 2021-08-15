package com.geektcp.alpha.socket.grpc.server;

import com.geektcp.alpha.socket.grpc.annotation.RpcService;
import com.geektcp.alpha.socket.grpc.proto.file.FileData;
import com.geektcp.alpha.socket.grpc.proto.file.FileServiceGrpc;
import com.geektcp.alpha.socket.grpc.proto.file.Response;
import com.google.protobuf.ByteString;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Objects;

/**
 * @author tanghaiyang on 2020/1/2 1:18.
 */
@Slf4j
@RpcService
public class FileServiceImpl extends FileServiceGrpc.FileServiceImplBase {

    private static final String SAVE_PATH = "data/";

    private static FileChannel fileChannel;
    private static FileOutputStream dstFos;

    @Override
    public void send(FileData fileData, StreamObserver<Response> responseObserver) {
        String fileName = fileData.getFileName();
        int status = fileData.getStatus();

        String message = "response";
        Response response = Response.newBuilder().setMsg(message).build();
        log.info("server responded {}", response);

        try {
            FileChannel dstFileChannel = getFileChannel(fileName, status);
            if (Objects.isNull(dstFileChannel)) {
                responseObserver.onNext(response);
                responseObserver.onCompleted();
                return;
            }
            ByteString data = fileData.getData();
            if(Objects.isNull(data)){
                responseObserver.onNext(response);
                responseObserver.onCompleted();
                return;
            }
            ByteBuffer buffer = ByteBuffer.wrap(data.toByteArray());
            dstFileChannel.write(buffer);

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            log.error(e.getMessage());
            log.info("fileName: {} | status:{}", fileName, status);
            e.printStackTrace();
        }
    }

    @Override
    public void getProgress(FileData request, StreamObserver<Response> responseObserver) {
        super.getProgress(request, responseObserver);
    }

    //////////////////////
    private static FileChannel getFileChannel(String fileName, int status) throws Exception {
        if (status == 0 || Objects.isNull(fileChannel)) {
            try {
                File dstFile = new File(SAVE_PATH + fileName);
                dstFos = new FileOutputStream(dstFile,true);
                fileChannel = dstFos.getChannel();
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        if (status == 2 && Objects.nonNull(fileChannel)) {
            fileChannel.close();
            return null;
        }
        return fileChannel;
    }
}
