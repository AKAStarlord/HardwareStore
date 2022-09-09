package com.dalehardware;

import java.util.HashMap;

/**
 * Instead of an actual database (outside the scope of this demo) the data and relationships between tools and their
 * pricing and holiday/weekend/weekday policy are held here.
 */
public class Inventory {

    // Based on the design doc it didn't seem like the contents of tool inventory would
    // ever change, so I just made a static map to hold them in. Additionally using a
    // map means that the IDs are unique.
    // I kept the tool code as a part of the Tool object, for "future-proofing" in case
    // of a theoretical scenario where someone would need a Tool object without already
    // knowing what the code was.
    public static final HashMap<String, Tool> tools = new HashMap<>() {{
        put("CHNS", new Tool("CHNS", ToolType.Chainsaw, Brand.Stihl));
        put("LADW", new Tool("LADW", ToolType.Ladder, Brand.Werner));
        put("JAKD", new Tool("JAKD", ToolType.Jackhammer, Brand.DeWalt));
        put("JAKR", new Tool("JAKR", ToolType.Jackhammer, Brand.Ridgid));
    }};

    // Since charge-policy is based on tool type I decided to make it a map as well.
    public static final HashMap<ToolType, ToolPolicy> policy = new HashMap<>() {{
       put(ToolType.Ladder, new ToolPolicy(1.99, true, true, false));
       put(ToolType.Chainsaw, new ToolPolicy(1.49, true, false, true));
       put(ToolType.Jackhammer, new ToolPolicy(2.99, true, false, false));
    }};

    // Because the types of tools seems pretty static I decided to
    // represent the tool type as an enum. This seems safer than comparing strings.
    // I added a string label member for when it comes time to print to the user.
    public enum ToolType {
        Chainsaw("Chainsaw"),
        Ladder("Ladder"),
        Jackhammer("Jackhammer");

        public final String label;

        ToolType(String label) {
            this.label = label;
        }
    }

    // Similar to tool type above, it is safer to use an enum.
    public enum Brand {
        Stihl("Stihl"),
        Werner("Werner"),
        DeWalt("DeWalt"),
        Ridgid("Ridgid");

        public final String label;

        Brand(String label) {
            this.label = label;
        }
    }
}
