package xyz.xenondevs.simpleupgrades.registry

import xyz.xenondevs.nova.material.NovaMaterialRegistry
import xyz.xenondevs.simpleupgrades.SimpleUpgrades

object GUIMaterials {
    
    val SPEED_UPGRADE = NovaMaterialRegistry.registerUnnamedHiddenItem(SimpleUpgrades, "gui_speed_upgrade")
    val EFFICIENCY_UPGRADE = NovaMaterialRegistry.registerUnnamedHiddenItem(SimpleUpgrades, "gui_efficiency_upgrade")
    val ENERGY_UPGRADE = NovaMaterialRegistry.registerUnnamedHiddenItem(SimpleUpgrades, "gui_energy_upgrade")
    val RANGE_UPGRADE = NovaMaterialRegistry.registerUnnamedHiddenItem(SimpleUpgrades, "gui_range_upgrade")
    val FLUID_UPGRADE = NovaMaterialRegistry.registerUnnamedHiddenItem(SimpleUpgrades, "gui_fluid_upgrade")
    
    fun init() = Unit
    
}