package negotiation.db.exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback=false)
public class AgentException extends RuntimeException {
    public AgentException() {
        super();
    }
}
