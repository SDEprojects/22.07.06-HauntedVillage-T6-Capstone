package com.game.hauntedVillage.model;

class Art {

//    public static ArrayList artGenerator(String artItem) {
//        ObjectMapper mapper = new ObjectMapper();
//        ArrayList<ArrayList<String>> result = new ArrayList<>();
//        ArrayList<String> art = new ArrayList<>();
//
//        try{
//            JsonNode rootArray = mapper.readTree(new File("resources/art.json"));
//            for (JsonNode root : rootArray) {
//                // Get Name
//                JsonNode nameNode = root.path(artItem);
//
//                if (!nameNode.isMissingNode()) {  // if "name" node is not missing
//                    for (JsonNode node : nameNode){
//                        art.add(node.asText());
//                    }
//                    result.add(art);
//                } else {
//                    System.out.println("No art to display");
//                }
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    public static void showArt(String artItem){
//        ArrayList<ArrayList<String>> result;
//        result = Art.artGenerator(artItem);
//        String art = result.get(0).get(0);
//        System.out.println(art);
//    }
}