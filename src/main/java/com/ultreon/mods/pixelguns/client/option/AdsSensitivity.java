package com.ultreon.mods.pixelguns.client.option;

import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.text.Text;

public class AdsSensitivity {
    private static final SimpleOption<Double> adsSensitivity = new SimpleOption<>("options.ads_sensitivity", SimpleOption.emptyTooltip(), (text, value) -> {
        if (value == 0.0) {
            return GameOptions.getGenericValueText(text, Text.translatable("options.sensitivity.min"));
        }
        if (value == 1.0) {
            return GameOptions.getGenericValueText(text, Text.translatable("options.sensitivity.max"));
        }
        return AdsSensitivity.getPercentValueText(text, 2.0 * value);
    }, SimpleOption.DoubleSliderCallbacks.INSTANCE, 0.5, double_ -> {});

    private static Text getPercentValueText(Text text, double d) {
        return Text.translatable("options.percent_value", text, (int)(d * 100.0));
    }

    public static SimpleOption<Double> getOption() {
        return adsSensitivity;
    }

    public static double getValue() {
        return adsSensitivity.getValue() * 2;
    }
}