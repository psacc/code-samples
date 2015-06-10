package koinos.web.controller;

import koinos.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

@Controller
@RequestMapping("generic")
public class GenericController{
        
	@Autowired
	private GenericService genericService;
    
    @RequestMapping("dettaglio/{type}")
    @ResponseBody
    public Object dettaglio(@PathVariable("type") String type, @RequestParam(value="id") BigDecimal id) throws Exception {
        return genericService.dettaglio(type, id);
    }
    
    @RequestMapping("dettaglioS/{type}")
    @ResponseBody
    public Object dettaglioS(@PathVariable("type") String type, @RequestParam(value="id") String id) throws Exception {
        return genericService.dettaglioS(type, id);
    }
}
