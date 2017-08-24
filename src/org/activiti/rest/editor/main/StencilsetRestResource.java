/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.activiti.rest.editor.main;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 

import org.activiti.engine.ActivitiException;
import org.apache.commons.io.IOUtils;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
 

/**
 * @author Tijs Rademakers
 */
@Controller
public class StencilsetRestResource {
  
  @RequestMapping(value="/editor/stencilset", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
  public void getStencilset(HttpServletRequest request, HttpServletResponse response) {
    InputStream stencilsetStream = this.getClass().getClassLoader().getResourceAsStream("stencilset.json");
    
/*    response.setContentType("text/html; charset=utf-8"); html  
    .setContentType("text/plain; charset=utf-8"); 文本  
    text/javascript json数据  
    application/xml  xml数据  */
    PrintWriter out = null;  
    try {  
    	String objStr= IOUtils.toString(stencilsetStream, "utf-8");
        //将实体对象转换为JSON Object转换  
        JSONObject responseJSONObject =new JSONObject(objStr);
        response.setCharacterEncoding("UTF-8");  
        response.setContentType("application/json; charset=utf-8");  
        
        out = response.getWriter();  
        out.append(responseJSONObject.toString());  
     
    } catch (Exception e) {  
        e.printStackTrace();  
    } finally {  
        if (out != null) {  
            out.close();  
        }  
    }  
    
  /*  
    try {
      String objStr= IOUtils.toString(stencilsetStream, "utf-8");
      response.setContentType("application/json; charset=UTF-8");
		JsonUtils.writeValue(response.getWriter(), objStr);
    } catch (Exception e) {
      throw new ActivitiException("Error while loading stencil set", e);
    }*/
  }
}
