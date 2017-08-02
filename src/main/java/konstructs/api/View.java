package konstructs.api;

import akka.actor.ActorRef;
import konstructs.api.messages.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * View is a class that represents the HUD as shown to the user
 * The HUD has 14 rows and 17 columns. To populate the View
 * one can place Inventories into the View with the help of
 * the InventoryView class.
 *
 * The different parts of the HUD is accessed through the HUD index
 * this index is a simple index treating the HUD as a flat array,
 * rather than a 2 dimensional surface. View and InventoryView automatically
 * takes care of all the conversions between HUD indexes and the 2 dimensional
 * coordinates of the actual on screen HUD.
 *
 * @see Inventory
 * @see InventoryView
 */
public final class View {
    public static final int COLUMNS = 17;
    public static final int ROWS = 14;

    private static final Map<Integer, Stack> EMPTY_MAP = Collections.emptyMap();
    /**
     * Definition of an empty view with no content. This is
     * the starting point to create a view.
     */
    public static final View EMPTY = new View(EMPTY_MAP);

    /**
     * Handle messages ({@link PutViewStack} or {@link RemoveViewStack}) from the player UI (HUD) using inventories
     * managed by the server and return true if a message was handled and false if the message could not be handled.
     *
     * This function works exactly like
     * {@link #manageViewMessagesForInventories(Object, UUID, Map, ActorRef, ActorRef, ActorRef)}, but with the
     * receiveStack and receiveViewUpdate set to the player parameter.
     * @param message The message to be managed
     * @param blockId The blockId of the block that contains the inventories
     * @param inventoryViewMapping A mapping between {@link InventoryId} and {@link InventoryView} used to see if the
     *                             message can be handled
     * @param universe An ActorRef to the unvierse actor
     * @param player An ActorRef to the actor that will receive the {@link ReceiveStack} and {@link UpdateView} messages
     * @return True if the message was handled, oterwise false
     * @see #manageViewMessagesForInventories(Object, UUID, Map, ActorRef, ActorRef, ActorRef)
     */

    public static boolean manageViewMessagesForInventories(Object message, UUID blockId, Map<InventoryId, InventoryView> inventoryViewMapping, ActorRef universe, ActorRef player) {
        return manageViewMessagesForInventories(message, blockId, inventoryViewMapping, universe, player, player);
    }


    /**
     * Handle messages ({@link PutViewStack} or {@link RemoveViewStack}) from the player UI (HUD) using inventories
     * managed by the server and return true if a message was handled and false if the message could not be handled.
     *
     * This function will check the inventoryViewMapping for an {@link InventoryView} that contains the position of
     * either the {@link PutViewStack} or {@link RemoveViewStack} message and generate a {@link PutStackIntoSlot} or
     * {@link RemoveStackFromSlot} message correspondingly. This message will be sent to the universe parameter.
     * It will then generate a {@link GetInventoriesView} message which will also be sent to the universe parameter.
     * The response to the first message (a {@link ReceiveStack}) will be sent to the receiveStack parameter.
     * The response to the second message (an {@link UpdateView}) will be sent to the receiveViewUpdate parameter.
     * @param message The message to be managed
     * @param blockId The blockId of the block that contains the inventories
     * @param inventoryViewMapping A mapping between {@link InventoryId} and {@link InventoryView} used to see if the
     *                             message can be handled
     * @param universe An ActorRef to the unvierse actor
     * @param receiveStack An ActorRef to the actor that will receive the {@link ReceiveStack} message
     * @param receiveViewUpdate An ActorRef to the actor that will receive the {@link UpdateView} message
     * @return True if the message was handled, oterwise false
     * @see PutViewStack
     * @see PutStackIntoSlot
     * @see RemoveViewStack
     * @see RemoveStackFromSlot
     * @see #manageViewMessagesForInventories(Object, UUID, Map, ActorRef, ActorRef)
     */
    public static boolean manageViewMessagesForInventories(Object message, UUID blockId, Map<InventoryId, InventoryView> inventoryViewMapping, ActorRef universe, ActorRef receiveStack, ActorRef receiveViewUpdate) {
        if(message instanceof PutViewStack) {
            PutViewStack putViewStack = (PutViewStack)message;
            for (Map.Entry<InventoryId, InventoryView> e : inventoryViewMapping.entrySet()) {
                if (e.getValue().contains(putViewStack.getPosition())) {
                    universe.tell(new PutStackIntoSlot(blockId, e.getKey(), e.getValue().translate(putViewStack.getPosition()), putViewStack.getStack()), receiveStack);
                    universe.tell(new GetInventoriesView(blockId, inventoryViewMapping), receiveViewUpdate);
                }
            }
            return true;
        } else if (message instanceof RemoveViewStack) {
            RemoveViewStack removeViewStack = (RemoveViewStack)message;
            for (Map.Entry<InventoryId, InventoryView> e : inventoryViewMapping.entrySet()) {
                if (e.getValue().contains(removeViewStack.getPosition())) {
                    universe.tell(new RemoveStackFromSlot(blockId, e.getKey(), e.getValue().translate(removeViewStack.getPosition()), removeViewStack.getAmount()), receiveStack);
                    universe.tell(new GetInventoriesView(blockId, inventoryViewMapping), receiveViewUpdate);
                }
            }
            return true;
        }
        return false;
    }

    private final Map<Integer, Stack> items;

    private View(Map<Integer, Stack> items) {
        this.items = items;
    }

    /**
     * Get the mapping between HUD indexes and stacks of this view
     * @return A mapping from HUD index to Stack
     * @see Stack
     */
    public Map<Integer, Stack> getItems() {
        return items;
    }

    /**
     * Add a block to this View
     * Returns a new view to which the block has been added
     * @param inventoryView The InventoryView that defines where in the HUD
     *                      the block should be shown as well as the dimensions
     *                      of it (must be 1x1 here)
     * @param block The block to show in the HUD
     * @return A new View that shows the block at the given positios
     * @see InventoryView
     */
    public View add(InventoryView inventoryView, Block block) {
        return add(inventoryView, Stack.createFromBlock(block));
    }

    /**
     * Add a stack to this View
     * Returns a new view to which the stack has been added
     * @param inventoryView The InventoryView that defines where in the HUD
     *                      the stack should be shown as well as the dimensions
     *                      of it (must be 1x1 here)
     * @param stack The stack to show in the HUD
     * @return A new View that shows the stack at the given positios
     * @see InventoryView
     */
    public View add(InventoryView inventoryView, Stack stack) {
        Stack[] stacks = {stack};
        return add(inventoryView, stacks);
    }

    /**
     * Add an array of stacks to this View
     * Returns a new view to which the array stacks has been added
     * @param inventoryView The InventoryView that defines where in the HUD
     *                      the inventory should be shown as well as the dimensions
     *                      of it
     * @param stacks The array of stacks to show in the HUD
     * @return A new View that shows the given array of stacks at the given positios
     * @see InventoryView
     */
    public View add(InventoryView inventoryView, Stack[] stacks) {
        Map<Integer, Stack> newItems = new HashMap<>(items);
        for(int row = 0; row < inventoryView.getRows(); row++) {
            for (int column = 0; column < inventoryView.getColumns(); column++) {
                int r = row + inventoryView.getRowOffset();
                int c = column + inventoryView.getColumnOffset();
                newItems.put(r * COLUMNS + c, stacks[row * inventoryView.getColumns() + column]);
            }
        }
        return new View(newItems);
    }

    /**
     * Add an inventory to this View
     * Returns a new view to which the Inventory has been added
     * @param inventoryView The InventoryView that defines where in the HUD
     *                      the inventory should be shown as well as the dimensions
     *                      of it
     * @param inventory The Inventory to show in the HUD
     * @return A new View that shows the given inventory at the given position
     * @see Inventory
     * @see InventoryView
     */
    public View add(InventoryView inventoryView, Inventory inventory) {
        return add(inventoryView, inventory.getStacks());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        View view = (View) o;

        return items.equals(view.items);

    }

    @Override
    public int hashCode() {
        return items.hashCode();
    }

    @Override
    public String toString() {
        return "View(" +
                "items=" + items +
                ')';
    }
}
