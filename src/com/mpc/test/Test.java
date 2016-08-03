package com.mpc.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mpc.todo.service.TodoDto;
import com.mpc.todo.service.TodoService;

 
public class Test {
	private static ApplicationContext context = null;

	public static void main(String[] args) throws Exception{
		
		try {
			ApplicationContext context = getContext();
			TodoService todoImpl = (TodoService)context.getBean("todoImpl");
			//TodoVo vo = new TodoVo();
			//System.out.println(JSONUtil.toJSON(todoImpl.getTodoList(vo)));
			for(int i=0; i<200; i++){
				TodoDto dto = new TodoDto();
				dto.setText(String.valueOf(i));
				System.out.println(todoImpl.todoProcess(dto));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static public ApplicationContext getContext(){
		String[] config = new String[]{"com/mpc/test/application-config-test.xml"};
		if(context == null){
			context = new ClassPathXmlApplicationContext(config);
		}
		return context;
	}	
}