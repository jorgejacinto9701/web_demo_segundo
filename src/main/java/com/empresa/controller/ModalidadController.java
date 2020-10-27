package com.empresa.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.empresa.entity.Deporte;
import com.empresa.entity.Modalidad;
import com.empresa.service.DeporteService;
import com.empresa.service.ModalidadService;

@Controller
public class ModalidadController {
	
	@Autowired
	private DeporteService deporteService;
	
	@Autowired
	private ModalidadService modalidadService;
	
	
	@RequestMapping("/verModalidad")
	public String ver() {
		return "registraModalidad";
	}
	
	@RequestMapping("/cargaDeporte")
	@ResponseBody
	public List<Deporte> listaDeporte() {
		return deporteService.listaDeporte();
	}
	
	
	@RequestMapping("/insertaModalidad")
	public String regModalidad(Modalidad obj, HttpSession session) {
		try {
			Modalidad objSalida = modalidadService.insertaModalidad(obj);
			if(objSalida != null) {
				session.setAttribute("MENSAJE","Se registró correctamente");
			}else {
				session.setAttribute("MENSAJE","Error al registrar");
			}
		} catch (Exception e) {
			session.setAttribute("MENSAJE","Error al registrar");
			e.printStackTrace();
		}
		return "redirect:salida";
	}
	
	@RequestMapping("/salida")
	public String salida() {
		return "registraModalidad";
	}
}
