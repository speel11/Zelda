package com.mygdx.entity;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.mygdx.util.AssetManager;

/**
 * @author Sean Peel
 */
public class Room {

    TiledMap map;
    OrthogonalTiledMapRenderer renderer;
    OrthographicCamera camera;
    float unitScale = 1;

    public Room(String id, OrthographicCamera camera) {
        this.camera = camera;
        map = AssetManager.getRoom(id);
        renderer = new OrthogonalTiledMapRenderer(map, unitScale);
        renderer.setView(camera);
        
    }
    
    public TiledMapTileLayer getCollisionLayer() {
        return (TiledMapTileLayer) map.getLayers().get("Walls");
    }
    
    public void render() {
        
        renderer.render();
    }
    
    public void dispose() {
        renderer.dispose();
        map.dispose();
        
    }
}
