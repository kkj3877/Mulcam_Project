package Control;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

public class BeanUtil {
	// request 의 parameter 들을 매개변수로 하는 vo 의 Setter 들을 모두 호출하여
	// 인스턴스를 초기화시켜주는 함수
	public static void populate(HttpServletRequest request, Object vo)
	{
		Class<?> cls = vo.getClass();
		Method[] mtds = cls.getMethods();
		
		for ( Method mtd : mtds ) {
			String setterName = mtd.getName();
			Class<?>[] ptypes = mtd.getParameterTypes();
			
			// Setter 들을 모두 찾아 호출한다(인스턴스를 초기화 한다)
			if ( setterName.startsWith("set") && ptypes.length == 1 ) {
				String paramName = propertyOfSetter( setterName );
				String val = request.getParameter(paramName);
				
				try {
					if ( val == null ) {
						mtd.invoke( vo, new Object[] { null } );
					}
					else if ( ptypes[0] == String.class ) {
						mtd.invoke( vo, Util.han(val) ); // 한글 문제는 여기서 해결
					}
					else if ( ptypes[0] == Integer.class ) {
						int intVal = -1;
						try { intVal = Integer.parseInt( val );	}
						catch ( NumberFormatException e ) { e.printStackTrace(); }
						finally { mtd.invoke( vo, intVal );	}
					}
				}
				catch ( Exception e ) { e.printStackTrace(); }
			}
		}
	}
	
	// Setter 에서 property 이름을 추출하는 함수
	public static String propertyOfSetter( String setterName ) {
		System.out.println(setterName.substring(3, 4).toLowerCase() + setterName.substring(4));
		return setterName.substring(3, 4).toLowerCase() + setterName.substring(4);
	}
}
