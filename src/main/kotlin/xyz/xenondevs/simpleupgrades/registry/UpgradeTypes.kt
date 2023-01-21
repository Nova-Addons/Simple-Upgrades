package xyz.xenondevs.simpleupgrades.registry

import xyz.xenondevs.nova.tileentity.upgrade.UpgradeTypeRegistry.register
import xyz.xenondevs.simpleupgrades.SimpleUpgrades

object UpgradeTypes {
    
    val SPEED = register<Double>(SimpleUpgrades, "speed", Items.SPEED_UPGRADE, GUIMaterials.SPEED_UPGRADE)
    val EFFICIENCY = register<Double>(SimpleUpgrades, "efficiency", Items.EFFICIENCY_UPGRADE, GUIMaterials.EFFICIENCY_UPGRADE)
    val ENERGY = register<Double>(SimpleUpgrades, "energy", Items.ENERGY_UPGRADE, GUIMaterials.ENERGY_UPGRADE)
    val FLUID = register<Double>(SimpleUpgrades, "fluid", Items.FLUID_UPGRADE, GUIMaterials.FLUID_UPGRADE)
    val RANGE = register<Int>(SimpleUpgrades, "range", Items.RANGE_UPGRADE, GUIMaterials.RANGE_UPGRADE)
    
    fun init() = Unit
    
}