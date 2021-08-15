package com.geektcp.alpha.spring.shiro.endpoint;

import com.geektcp.alpha.spring.shiro.annotation.AlphaEndPoint;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;

import java.util.List;

/**
 * @author tanghaiyang
 */
@AlphaEndPoint
public class AlphaHttpTraceEndpoint {

    private final HttpTraceRepository repository;

    public AlphaHttpTraceEndpoint(HttpTraceRepository repository) {
        this.repository = repository;
    }

    public AlphaHttpTraceDescriptor traces() {
        return new AlphaHttpTraceDescriptor(this.repository.findAll());
    }

    public static final class AlphaHttpTraceDescriptor {

        private final List<HttpTrace> traces;

        private AlphaHttpTraceDescriptor(List<HttpTrace> traces) {
            this.traces = traces;
        }

        public List<HttpTrace> getTraces() {
            return this.traces;
        }
    }
}
