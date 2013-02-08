package org.ncbo.stanford.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.mapper.CannotResolveClassException;
import com.thoughtworks.xstream.mapper.MapperWrapper;

public class XStreamUtil {

    /**
     * Creates an {@link XStream} object that ignores unknown XML elements.
     *
     * See bug report and solution from:
     * <a href="http://jira.codehaus.org/browse/XSTR-30">http://jira.codehaus.org/browse/XSTR-30</a>
     *
     * @return - a {@link XStream} object that handles unknown XML elements
     */
    public static XStream createXStream() {
        XStream xStream = new XStream() {
           @Override
           protected MapperWrapper wrapMapper(MapperWrapper next) {
               return new MapperWrapper(next) {
                   @Override
                @SuppressWarnings("unchecked")
                   public boolean shouldSerializeMember(Class definedIn, String fieldName) {
                       try {
                           return definedIn != Object.class || realClass(fieldName) != null;
                       } catch(CannotResolveClassException cnrce) {
                           return false;
                       }
                   }
               };
           }
        };
        return xStream;
    }

}
