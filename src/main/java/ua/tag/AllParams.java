package ua.tag;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class AllParams extends SimpleTagSupport {

    private final StringWriter sw = new StringWriter();
    private static final String AMPER = "&";
    private static final String QUEST = "?";
    private static final String EQUAL = "=";

    @Override
    public void doTag() throws IOException {
        JspWriter out = getJspContext().getOut();
        PageContext pageContext = (PageContext) getJspContext();
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        Map<String, String[]> map = request.getParameterMap();
        boolean isFirst = true;
        for (Entry<String, String[]> entry : map.entrySet()) {
            for (String value : entry.getValue()) {
                if (isFirst) {
                    sw.append(QUEST);
                    isFirst = false;
                } else {
                    sw.append(AMPER);
                }
                sw.append(entry.getKey());
                sw.append(EQUAL);
                sw.append(value);
            }
        }
        out.println(sw.toString());
    }
}