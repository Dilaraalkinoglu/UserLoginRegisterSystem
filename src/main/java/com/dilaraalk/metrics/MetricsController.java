package com.dilaraalk.metrics;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class MetricsController {
	
	private final MetricService metricService;
	
	public MetricsController(MetricService metricService) {
		this.metricService = metricService;
	}
	
	@GetMapping("/metrics")
	public ResponseEntity<Map<String, Object>> getMetrics(){
		long userCount = metricService.getTotalUserCount();
		
		return ResponseEntity.ok(Map.of("totalUserCount", userCount));
		
	}
	

}
