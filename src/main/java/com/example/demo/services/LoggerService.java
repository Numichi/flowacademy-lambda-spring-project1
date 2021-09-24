package com.example.demo.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class LoggerService {
    private static final Logger system = LogManager.getLogger("SYSTEM");
    private static final Logger audit = LogManager.getLogger("AUDIT");

    public void auditInfo(String message) {
        audit.info(message);
    }

    public void systemError(String message) {
        system.error(message);
    }
}
