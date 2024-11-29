package org.osmdroid.views.overlay.advancedpolyline;

import java.util.Map;
import java.util.List;

/**
 * Color mapping to map ranges to specific colors.
 *
 * @author Matthias Dittmer
 */
public class ColorMappingRanges extends ColorMappingForScalar {

    /**
     * Using a sorted map to define borders of ranges.
     * Borders are sorted from low to high.
     */
    private final List<Map.Entry<Float, Integer>> mColorRanges;
    private final boolean mStrictComparison;

    public ColorMappingRanges(final List<Map.Entry<Float, Integer>> pColorArray, final boolean pStrictComparison) {
        mColorRanges = pColorArray;
        mStrictComparison = pStrictComparison;
    }

    @Override
    protected int computeColor(final float pScalar) {
        int lastArrayIndexFromLoop = 0;
        // iterate over array and sort point in
        for (Map.Entry<Float, Integer> entry : mColorRanges) {

            if (mStrictComparison) {
                if (pScalar < entry.getKey()) {
                    return entry.getValue();
                }
            } else {
                if (pScalar <= entry.getKey()) {
                    return entry.getValue();
                }
            }
            lastArrayIndexFromLoop++;
            // assign last color if scalar is above highest border
            if (lastArrayIndexFromLoop == mColorRanges.size()) {
                return entry.getValue();
            }
        }
        return 0;
    }
}
