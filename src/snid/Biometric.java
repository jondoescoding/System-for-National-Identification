package snid;

/**
 * @author Aaliyah Johnston 620130550
 * @author <a href="mailto:jonathan.logos34@gmal.com">Jonathan White - 60129431</a>
 * @version 1.0
 * Interface
 */
public interface Biometric {
    String getTag();
    String getValue();
    int match(Biometric other);
}
