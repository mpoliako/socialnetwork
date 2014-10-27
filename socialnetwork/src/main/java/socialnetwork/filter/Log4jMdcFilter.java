package socialnetwork.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.apache.log4j.MDC;

@WebFilter(filterName  = "log4jMdcFilter", urlPatterns = {"/*"})
public class Log4jMdcFilter implements Filter {


  private static final class MdcKeys {
    /** Rid key. */
    private static final String RID_KEY = "rid";

    /**
     * Private constructor.
     */
    private MdcKeys() { }

  }

  /** {@inheritDoc} */
  @Override
  public void destroy() {

  }

  /** {@inheritDoc} */
  @Override
  public void doFilter(final ServletRequest request,
                       final ServletResponse response,
                       final FilterChain chain) throws IOException, ServletException {

    try {
      MDC.put(MdcKeys.RID_KEY, System.currentTimeMillis());
      chain.doFilter(request, response);
    } finally {
      MDC.remove(MdcKeys.RID_KEY);
    }
  }
  
  /**
   * Init method for this filter.
   * @param filterConfig the filter config
   */
  @Override
  public void init(final FilterConfig filterConfig) {
  }

}

