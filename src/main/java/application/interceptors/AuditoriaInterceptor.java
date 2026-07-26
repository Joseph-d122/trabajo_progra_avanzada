package application.interceptors;

import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import java.util.logging.Logger;

@Auditable
@Interceptor
public class AuditoriaInterceptor {

    private static final Logger LOG = Logger.getLogger(AuditoriaInterceptor.class.getName());

    @AroundInvoke
    public Object auditarMetodo(InvocationContext context) throws Exception {
        long inicio = System.currentTimeMillis();
        LOG.info("Iniciando transacción en: " + context.getMethod().getName());

        try {
            return context.proceed();
        } finally {
            long tiempo = System.currentTimeMillis() - inicio;
            LOG.info("Transacción completada en " + tiempo + " ms");
        }
    }
}
