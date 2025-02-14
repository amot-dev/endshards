import { defineConfig } from 'vitepress'

export default defineConfig({
    title: "Endshards Wiki",
    description: "The official wiki for the Endshards Minecraft mod.",
    base: '/endshards/',
    themeConfig: {
        nav: [
            { text: "Home", link: "/" },
            {
                text: "Versions",
                items: [
                    { text: "Endshards 0.3.0 for 1.21.4", link: "/" }
                    //{ text: "0.2.7", link: "https://github.com/amot-dev/endshards/tree/0.2.7/docs/" },
                    ]
                },
            { text: "Modrinth", link: "https://modrinth.com/mod/endshards" },
            { text: "GitHub", link: "https://github.com/amot-dev/endshards" }
            ],
        sidebar: [
            { text: "Home", link: "/" },
            {
                text: "Progression",
                items: [
                    { text: "Slaying the Dragon", link: "/progression/slaying_the_dragon" },
                    { text: "Back to the Nether", link: "/progression/back_to_the_nether" },
                    { text: "Depths of the Overworld", link: "/progression/depths_of_the_overworld" },
                    { text: "Preparing for the Nightmare", link: "/progression/preparing_for_the_nightmare" }
                    ]
                },
            {
                text: "Materials",
                items: [
                    { text: "Ores", link: "/materials/ores" },
                    { text: "Raw Items", link: "/materials/raw_items" },
                    { text: "Refined Items", link: "/materials/refined_items" },
                    { text: "Infusion", link: "/materials/infusion" }
                    ]
                },
            {
                text: "Equipment",
                items: [
                    { text: "Ender", link: "/equipment/ender" },
                    { text: "Netherite", link: "/equipment/netherite" },
                    { text: "Sculk", link: "/equipment/sculk" }
                    ]
                },
            {
                text: "Nightmare Realm",
                items: [
                    {
                        text: "Future Planned Content",
                        link: "/nightmare_realm/future",
                        items: [
                            { text: "Entrances and Exits", link: "/nightmare_realm/future/entrance_and_exits" },
                            { text: "Layout", link: "/nightmare_realm/future/layout" },
                            { text: "Gate of Terror", link: "/nightmare_realm/future/gate_of_terror" }
                            ]
                        }
                    ]
                }
            ]
        }
    }
);
