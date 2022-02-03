package Control;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

public class BeanUtil {
	// request �� parameter ���� �Ű������� �ϴ� vo �� Setter ���� ��� ȣ���Ͽ�
	// �ν��Ͻ��� �ʱ�ȭ�����ִ� �Լ�
	public static void populate(HttpServletRequest request, Object vo)
	{
		Class<?> cls = vo.getClass();
		Method[] mtds = cls.getMethods();
		
		for ( Method mtd : mtds ) {
			String setterName = mtd.getName();
			Class<?>[] ptypes = mtd.getParameterTypes();
			
			// Setter ���� ��� ã�� ȣ���Ѵ�(�ν��Ͻ��� �ʱ�ȭ �Ѵ�)
			if ( setterName.startsWith("set") && ptypes.length == 1 ) {
				String paramName = propertyOfSetter( setterName );
				String val = request.getParameter(paramName);
				
				try {
					if ( val == null ) {
						mtd.invoke( vo, new Object[] { null } );
					}
					else if ( ptypes[0] == String.class ) {
						mtd.invoke( vo, Util.han(val) ); // �ѱ� ������ ���⼭ �ذ�
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
	
	// Setter ���� property �̸��� �����ϴ� �Լ�
	public static String propertyOfSetter( String setterName ) {
		System.out.println(setterName.substring(3, 4).toLowerCase() + setterName.substring(4));
		return setterName.substring(3, 4).toLowerCase() + setterName.substring(4);
	}
}
