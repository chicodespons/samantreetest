package org.lawnpilot;

import java.util.List;

public record ParsedInput(Lawn lawn, List<MowerData> mowers) {
}
