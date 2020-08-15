package org.zhire.work.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

/**
 * @author
 */
@Slf4j
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponsePage<T> extends Response<ResponsePage.Page<T>> {

    public Optional<List<T>> getPageContent() {
        return Optional.ofNullable(getPayload().getContent());
    }

    @Data
    @Accessors(chain = true)
    @ToString(callSuper = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Page<T> {
        private List<T> content;
        private Boolean empty;
        private Boolean first;
        private Boolean last;
        private long number;
        private long numberOfElements;
        private Pageable pageable;
        private long size;
        private Sort sort;
        private long totalElements;
        private long totalPages;
    }

    @Data
    @Accessors(chain = true)
    @ToString(callSuper = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Pageable {
        private long offset;
        private long pageNumber;
        private long pageSize;
        private Boolean paged;
        private Sort sort;
        private Boolean unpaged;
    }

    @Data
    @Accessors(chain = true)
    @ToString(callSuper = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Sort {
        private Boolean empty;
        private Boolean sorted;
        private Boolean unsorted;
    }
}
