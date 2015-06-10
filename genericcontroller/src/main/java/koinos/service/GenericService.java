package koinos.service;

import org.apache.commons.beanutils.MethodUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.math.BigDecimal;

public class GenericService implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	public Object dettaglio(String type, BigDecimal id) throws Exception {
		return MethodUtils.invokeMethod(applicationContext.getBean(type + "Mapper"),
				"selectByPrimaryKey", id);
	}
	
	public Object dettaglioS(String type, String id) throws Exception {
		return MethodUtils.invokeMethod(applicationContext.getBean(type + "Mapper"),
				"selectByPrimaryKey", id);
	}

	@Override
	public void setApplicationContext(ApplicationContext ac)
			throws BeansException {
		this.applicationContext = ac;
	}

}
