package blocks;

/**
 * HitNotifier objects can notify HitListeners about hit events.
 */
public interface HitNotifier {
    /**
     * Adds a listener to be notified of hit events.
     *
     * @param hl the HitListener to add.
     */
    void addHitListener(HitListener hl);

    /**
     * Removes a listener from the notification list.
     *
     * @param hl the HitListener to remove.
     */
    void removeHitListener(HitListener hl);
}
