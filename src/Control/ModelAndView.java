package Control;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

public class ModelAndView {
	// Redirect or Dispatch �� ������ viewName
	private String viewName = null;
	public String getViewName() { return viewName; }
	public void setViewName(String viewName) { this.viewName = viewName; }
	
	// Controller �� ��ȯ���� �ִٸ� �װ��� ������ Map
	public Map<String, Object> objMap = null;
	public Map<String, Object> getMap() { return objMap; }
	public void addObject( String key, Object value ) {
		if ( objMap == null ) {
			objMap = new Hashtable<String, Object>();
		}
		objMap.put(key, value);
	}
	
	// Controller �� ��ȯ���� request�� Attribute �� �ɾ��ִ� �Լ�
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
