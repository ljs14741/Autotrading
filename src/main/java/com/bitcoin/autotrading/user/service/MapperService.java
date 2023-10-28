package com.bitcoin.autotrading.user.service;


import com.bitcoin.autotrading.common.ModelMapperUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
//import com.liac.lnc.common.error.ErrorCode;
//import com.liac.lnc.common.error.exception.BusinessException;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * ObjectMapper, ModelMapper, XmlMapper 의 주요 메소드를
 * 하나의 서비스로 사용하도록 + 별도 exception 처리 없이 사용할 수 있도록 구현한 Facade 서비스.
 */

@Slf4j
@Component
//@ComponentScan(basePackages = {"com.bitcoin.autotrading.user.service"})
public class MapperService {

//    private static final String FROM_JSON_ERROR_MESSAGE = "error occurred while reading json with content [{}]\n{}";
//    private static final String TO_JSON_ERROR_MESSAGE = "error occurred while parsing json with source [{}]\n{}";
//
//    private static final String FROM_XML_ERROR_MESSAGE = "error occurred while reading xml with content [{}]\n{}";
//    private static final String TO_XML_ERROR_MESSAGE = "error occurred while parsing xml with source [{}]\n{}";

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ModelMapper modelMapper;

    private XmlMapper xmlMapper;


//
//
//    public MapperService(ObjectMapper objectMapper, ModelMapper modelMapper,
//                         XmlMapper xmlMapper) {
//        this.objectMapper = objectMapper;
//        this.modelMapper = modelMapper;
//        this.xmlMapper = xmlMapper;
//    }

    public <D> D map(Object source, Class<D> destinationType) {
//        if (source == null) {
//            throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR, "message.model-mapper.source.null", new String[]{destinationType.getSimpleName()});
//        }
        return modelMapper.map(source, destinationType);
    }

    /**
     * List<Obj> to List<targetObj> 변환시 사용
     * targetObj (destinationType) 에 반드시 디폴트 생성자 있어야 함.
     */
    public <D, T> List<D> mapAll(final Collection<T> sourceList, Class<D> destinationType) {
//        if (sourceList == null) {
//            throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR, "message.model-mapper.source.null", new String[]{destinationType.getSimpleName()});
//        }
        return sourceList.stream().map(source -> map(source, destinationType))
                .collect(Collectors.toList());
    }

    /**
     *  modelMapper 에 룰 추가
//     */
//    public <S, D> TypeMap<S, D> addMappings(PropertyMap<S, D> propertyMap) {
//        return modelMapper.addMappings(propertyMap);
//    }
//
//    public void validateMapping() {
//        modelMapper.validate();
//    }
//
//    public String toJson(Object source) {
//        assertSourceNotNull(source);
//        try {
//            return objectMapper.writeValueAsString(source);
//        } catch (JsonProcessingException e) {
//            log.error(TO_JSON_ERROR_MESSAGE, source.toString(), e.toString(), e);
//            return "";
//        }
//    }
//
//    public String toJson(Object source, String resultWhenException) {
//        assertSourceNotNull(source);
//        try {
//            return objectMapper.writeValueAsString(source);
//        } catch (JsonProcessingException e) {
//            log.error(TO_JSON_ERROR_MESSAGE, source.toString(), e.toString(), e);
//            return resultWhenException;
//        }
//    }
//
//    public String toJsonPretty(Object source) {
//        assertSourceNotNull(source);
//        try {
//            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(source);
//        } catch (JsonProcessingException e) {
//            log.error(TO_JSON_ERROR_MESSAGE, source.toString(), e.toString(), e);
//            return "";
//        }
//    }
//
//    public <T> T fromJson(String content, Class<T> valueType) {
//        assertSourceNotNull(content);
//        try {
//            return objectMapper.readValue(content, valueType);
//        } catch (IOException e) {
//            log.error(FROM_JSON_ERROR_MESSAGE, content, e);
//            return null;
//        }
//    }
//
//    public <T> T fromJson(String content, TypeReference<T> valueTypeRef) {
//        assertSourceNotNull(content);
//        try {
//            return objectMapper.readValue(content, valueTypeRef);
//        } catch (IOException e) {
//            log.error(FROM_JSON_ERROR_MESSAGE, content, e);
//            return null;
//        }
//    }
//
//    public <T> T readValue(String content, JavaType valueType) {
//        assertSourceNotNull(content);
//        try {
//            return objectMapper.readValue(content, valueType);
//        } catch (IOException e) {
//            log.error(FROM_JSON_ERROR_MESSAGE, content, e);
//            return null;
//        }
//    }
//
//    public <T> T readValue(String content, Class<T> valueType) {
//        assertSourceNotNull(content);
//        try {
//            return objectMapper.readValue(content, valueType);
//        } catch (IOException e) {
//            log.error(FROM_JSON_ERROR_MESSAGE, content, e);
//            return null;
//        }
//    }
//
//    public <T> T readValue(String content, TypeReference<T> valueTypeRef) {
//        assertSourceNotNull(content);
//        try {
//            return objectMapper.readValue(content, valueTypeRef);
//        } catch (IOException e) {
//            log.error(FROM_JSON_ERROR_MESSAGE, content, e);
//            return null;
//        }
//    }
//
//    public <T> T readValue(byte[] content, JavaType valueType) {
//        assertSourceNotNull(content);
//        try {
//            return objectMapper.readValue(content, valueType);
//        } catch (IOException e) {
//            log.error(FROM_JSON_ERROR_MESSAGE, content, e);
//            return null;
//        }
//    }
//
//    public <T> T readValue(byte[] content, Class<T> valueType) {
//        assertSourceNotNull(content);
//        try {
//            return objectMapper.readValue(content, valueType);
//        } catch (IOException e) {
//            log.error(FROM_JSON_ERROR_MESSAGE, content, e);
//            return null;
//        }
//    }
//
//    public <T> T readValue(byte[] content, TypeReference<T> valueTypeRef) {
//        assertSourceNotNull(content);
//        try {
//            return objectMapper.readValue(content, valueTypeRef);
//        } catch (IOException e) {
//            log.error(FROM_JSON_ERROR_MESSAGE, content, e);
//            return null;
//        }
//    }
//
//    public JsonNode readTree(String content) {
//        assertSourceNotNull(content);
//        try {
//            return objectMapper.readTree(content);
//        } catch (IOException e) {
//            log.error(FROM_JSON_ERROR_MESSAGE, content, e);
//            return null;
//        }
//    }
//
//    public JsonNode readTree(byte[] content) {
//        assertSourceNotNull(content);
//        try {
//            return objectMapper.readTree(content);
//        } catch (IOException e) {
//            log.error(FROM_JSON_ERROR_MESSAGE, content, e);
//            return null;
//        }
//    }
//
//    // nested 구조가 아닌 json 에서 주어진 key 에 해당되는 value 값을 리턴 (없을경우 null 리턴)
//    public String getValueOfKeyFromPlainJson(String json, String key) {
//        if (StringUtils.isAnyBlank(json, key)) {
//            return null;
//        }
//        try {
//            JsonNode jsonNode = readTree(json);
//            return jsonNode.path(key).asText(null);
//        } catch (Exception e) {
//            log.error(FROM_JSON_ERROR_MESSAGE, json, e);
//            return null;
//        }
//    }
//
//    public ObjectNode createObjectNode() {
//        return objectMapper.createObjectNode();
//    }
//
//    public ArrayNode createArrayNode() {
//        return objectMapper.createArrayNode();
//    }
//
//    public <T> T convertValue(Object fromValue, Class<T> toValueType) {
//        return objectMapper.convertValue(fromValue, toValueType);
//    }
//
//    public <T> T convertValue(Object fromValue, TypeReference<T> toValueTypeRef) {
//        return objectMapper.convertValue(fromValue, toValueTypeRef);
//    }
//
//    public <T> T convertValue(Object fromValue, JavaType toValueType) {
//        return objectMapper.convertValue(fromValue, toValueType);
//    }
//
//    public TypeFactory getTypeFactory() {
//        return objectMapper.getTypeFactory();
//    }
//
//    public <T> T fromXml(String content, Class<T> valueType) {
//        assertSourceNotNull(content);
//        try {
//            return xmlMapper.readValue(content, valueType);
//        } catch (JsonProcessingException e) {
//            log.error(FROM_XML_ERROR_MESSAGE, content, e);
//            return null;
//        }
//    }
//
//    public <T> T fromXml(String content, TypeReference<T> valueTypeRef) {
//        assertSourceNotNull(content);
//        try {
//            return xmlMapper.readValue(content, valueTypeRef);
//        } catch (JsonProcessingException e) {
//            log.error(FROM_XML_ERROR_MESSAGE, content, e);
//            return null;
//        }
//    }
//
//    private void assertSourceNotNull(Object source) {
//        if (source == null) {
//            throw new BusinessException(ErrorCode.ILLEGAL_ARGUMENT, "message.mapper.source.null", null);
//        }
//    }

}







//-----------------------------
//
//package com.bitcoin.autotrading.user.service;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.JavaType;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.PropertyNamingStrategy;
//import com.fasterxml.jackson.databind.node.ArrayNode;
//import com.fasterxml.jackson.databind.node.ObjectNode;
//import com.fasterxml.jackson.databind.type.TypeFactory;
//import com.fasterxml.jackson.dataformat.xml.XmlMapper;
////import com.liac.lnc.common.error.ErrorCode;
////import com.liac.lnc.common.error.exception.BusinessException;
//import java.io.IOException;
//import java.util.Collection;
//import java.util.List;
//import java.util.stream.Collectors;
//import lombok.extern.slf4j.Slf4j;
////import org.apache.commons.lang3.StringUtils;
//import org.modelmapper.ModelMapper;
//import org.modelmapper.PropertyMap;
//import org.modelmapper.TypeMap;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Component;
//
//    /**
//     * ObjectMapper, ModelMapper, XmlMapper 의 주요 메소드를
//     * 하나의 서비스로 사용하도록 + 별도 exception 처리 없이 사용할 수 있도록 구현한 Facade 서비스.
//     */
//
//    @Slf4j
//    @Component
//    public class MapperService {
//
//        private static final String FROM_JSON_ERROR_MESSAGE = "error occurred while reading json with content [{}]\n{}";
//        private static final String TO_JSON_ERROR_MESSAGE = "error occurred while parsing json with source [{}]\n{}";
//
//        private static final String FROM_XML_ERROR_MESSAGE = "error occurred while reading xml with content [{}]\n{}";
//        private static final String TO_XML_ERROR_MESSAGE = "error occurred while parsing xml with source [{}]\n{}";
//
//        private final ObjectMapper objectMapper;
//        private final ModelMapper modelMapper;
//        private final XmlMapper xmlMapper;
//
//        @Autowired
//        public MapperService(ObjectMapper objectMapper, ModelMapper modelMapper,
//                             XmlMapper xmlMapper) {
//            this.objectMapper = objectMapper;
//            this.modelMapper = modelMapper;
//            this.xmlMapper = xmlMapper;
//        }
//
//        public <D> D map(Object source, Class<D> destinationType) {
////            if (source == null) {
////                throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR, "message.model-mapper.source.null", new String[]{destinationType.getSimpleName()});
////            }
//            return modelMapper.map(source, destinationType);
//        }
//
//        /**
//         * List<Obj> to List<targetObj> 변환시 사용
//         * targetObj (destinationType) 에 반드시 디폴트 생성자 있어야 함.
//         */
//        public <D, T> List<D> mapAll(final Collection<T> sourceList, Class<D> destinationType) {
////            if (sourceList == null) {
////                throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR, "message.model-mapper.source.null", new String[]{destinationType.getSimpleName()});
////            }
//            return sourceList.stream().map(source -> map(source, destinationType))
//                    .collect(Collectors.toList());
//        }
//
////        /**
////         *  modelMapper 에 룰 추가
////         */
////        public <S, D> TypeMap<S, D> addMappings(PropertyMap<S, D> propertyMap) {
////            return modelMapper.addMappings(propertyMap);
////        }
////
////        public void validateMapping() {
////            modelMapper.validate();
////        }
////
////        public String toJson(Object source) {
////            assertSourceNotNull(source);
////            try {
////                return objectMapper.writeValueAsString(source);
////            } catch (JsonProcessingException e) {
////                log.error(TO_JSON_ERROR_MESSAGE, source.toString(), e.toString(), e);
////                return "";
////            }
////        }
////
////        public String toJson(Object source, String resultWhenException) {
////            assertSourceNotNull(source);
////            try {
////                return objectMapper.writeValueAsString(source);
////            } catch (JsonProcessingException e) {
////                log.error(TO_JSON_ERROR_MESSAGE, source.toString(), e.toString(), e);
////                return resultWhenException;
////            }
////        }
////
////        public String toJsonPretty(Object source) {
////            assertSourceNotNull(source);
////            try {
////                return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(source);
////            } catch (JsonProcessingException e) {
////                log.error(TO_JSON_ERROR_MESSAGE, source.toString(), e.toString(), e);
////                return "";
////            }
////        }
////
////        public <T> T fromJson(String content, Class<T> valueType) {
////            assertSourceNotNull(content);
////            try {
////                return objectMapper.readValue(content, valueType);
////            } catch (IOException e) {
////                log.error(FROM_JSON_ERROR_MESSAGE, content, e);
////                return null;
////            }
////        }
////
////        public <T> T fromJson(String content, TypeReference<T> valueTypeRef) {
////            assertSourceNotNull(content);
////            try {
////                return objectMapper.readValue(content, valueTypeRef);
////            } catch (IOException e) {
////                log.error(FROM_JSON_ERROR_MESSAGE, content, e);
////                return null;
////            }
////        }
////
////        public <T> T readValue(String content, JavaType valueType) {
////            assertSourceNotNull(content);
////            try {
////                return objectMapper.readValue(content, valueType);
////            } catch (IOException e) {
////                log.error(FROM_JSON_ERROR_MESSAGE, content, e);
////                return null;
////            }
////        }
////
////        public <T> T readValue(String content, Class<T> valueType) {
////            assertSourceNotNull(content);
////            try {
////                return objectMapper.readValue(content, valueType);
////            } catch (IOException e) {
////                log.error(FROM_JSON_ERROR_MESSAGE, content, e);
////                return null;
////            }
////        }
////
////        public <T> T readValue(String content, TypeReference<T> valueTypeRef) {
////            assertSourceNotNull(content);
////            try {
////                return objectMapper.readValue(content, valueTypeRef);
////            } catch (IOException e) {
////                log.error(FROM_JSON_ERROR_MESSAGE, content, e);
////                return null;
////            }
////        }
////
////        public <T> T readValue(byte[] content, JavaType valueType) {
////            assertSourceNotNull(content);
////            try {
////                return objectMapper.readValue(content, valueType);
////            } catch (IOException e) {
////                log.error(FROM_JSON_ERROR_MESSAGE, content, e);
////                return null;
////            }
////        }
////
////        public <T> T readValue(byte[] content, Class<T> valueType) {
////            assertSourceNotNull(content);
////            try {
////                return objectMapper.readValue(content, valueType);
////            } catch (IOException e) {
////                log.error(FROM_JSON_ERROR_MESSAGE, content, e);
////                return null;
////            }
////        }
////
////        public <T> T readValue(byte[] content, TypeReference<T> valueTypeRef) {
////            assertSourceNotNull(content);
////            try {
////                return objectMapper.readValue(content, valueTypeRef);
////            } catch (IOException e) {
////                log.error(FROM_JSON_ERROR_MESSAGE, content, e);
////                return null;
////            }
////        }
////
////        public JsonNode readTree(String content) {
////            assertSourceNotNull(content);
////            try {
////                return objectMapper.readTree(content);
////            } catch (IOException e) {
////                log.error(FROM_JSON_ERROR_MESSAGE, content, e);
////                return null;
////            }
////        }
////
////        public JsonNode readTree(byte[] content) {
////            assertSourceNotNull(content);
////            try {
////                return objectMapper.readTree(content);
////            } catch (IOException e) {
////                log.error(FROM_JSON_ERROR_MESSAGE, content, e);
////                return null;
////            }
////        }
////
////        // nested 구조가 아닌 json 에서 주어진 key 에 해당되는 value 값을 리턴 (없을경우 null 리턴)
////        public String getValueOfKeyFromPlainJson(String json, String key) {
////            if (StringUtils.isAnyBlank(json, key)) {
////                return null;
////            }
////            try {
////                JsonNode jsonNode = readTree(json);
////                return jsonNode.path(key).asText(null);
////            } catch (Exception e) {
////                log.error(FROM_JSON_ERROR_MESSAGE, json, e);
////                return null;
////            }
////        }
////
////        public ObjectNode createObjectNode() {
////            return objectMapper.createObjectNode();
////        }
////
////        public ArrayNode createArrayNode() {
////            return objectMapper.createArrayNode();
////        }
////
////        public <T> T convertValue(Object fromValue, Class<T> toValueType) {
////            return objectMapper.convertValue(fromValue, toValueType);
////        }
////
////        public <T> T convertValue(Object fromValue, TypeReference<T> toValueTypeRef) {
////            return objectMapper.convertValue(fromValue, toValueTypeRef);
////        }
////
////        public <T> T convertValue(Object fromValue, JavaType toValueType) {
////            return objectMapper.convertValue(fromValue, toValueType);
////        }
////
////        public TypeFactory getTypeFactory() {
////            return objectMapper.getTypeFactory();
////        }
////
////        public <T> T fromXml(String content, Class<T> valueType) {
////            assertSourceNotNull(content);
////            try {
////                return xmlMapper.readValue(content, valueType);
////            } catch (JsonProcessingException e) {
////                log.error(FROM_XML_ERROR_MESSAGE, content, e);
////                return null;
////            }
////        }
////
////        public <T> T fromXml(String content, TypeReference<T> valueTypeRef) {
////            assertSourceNotNull(content);
////            try {
////                return xmlMapper.readValue(content, valueTypeRef);
////            } catch (JsonProcessingException e) {
////                log.error(FROM_XML_ERROR_MESSAGE, content, e);
////                return null;
////            }
////        }
////
////        private void assertSourceNotNull(Object source) {
////            if (source == null) {
////                throw new BusinessException(ErrorCode.ILLEGAL_ARGUMENT, "message.mapper.source.null", null);
////            }
////        }
//
//    }