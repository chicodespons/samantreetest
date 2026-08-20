package org.lawnpilot.application;

import org.lawnpilot.domain.Lawn;

import java.util.List;

public record ParsedInput(Lawn lawn, List<MowerData> mowers) {
}
