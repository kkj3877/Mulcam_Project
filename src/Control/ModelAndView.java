package Control;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

public class ModelAndView {
	// Redirect or Dispatch 를 결정할 viewName
	private String viewName = null;
	public String getViewName() { return viewName; }
	public void setViewName(String viewName) { this.viewName = viewName; }
	
	// Controller 에 반환값이 있다면 그것을 저장할 Map
	public Map<String, Object> objMap = null;
	public Map<String, Object> getMap() { return objMap; }
	public void addObject( String key, Object value ) {
		if ( objMap == null ) {
			objMap = new Hashtable<String, Object>();
		}
		objMap.put(key, value);
	}
	
	// Controller 의 반환값을 request의 Attribute 로 심어주는 함수
	public void plant(HttpServletRequest request) {
		if ( objMap != null ) {
			Set<String> keySet = objMap.keySet();
			for ( String key : keySet ) {
				Object val = objMap.get(key);
				request.setAttribute(key, val);
			}
		}
	}
}
