package Movable;

import Board.Game;

import java.awt.*;

public abstract class ArmedGhost extends Ghost {
    private boolean _loaded = false;
    private static final double _reload = 5;
    private static final double _weaponize = 2;
    private double _weaponTicks = 0;

    ArmedGhost(Game master, Point position, double velocity, String[] path) {
        super(master,position, velocity, path);

    }

    @Override
    public void Tick(double tick){
        IncreaseTicksBy(tick);
        if (is_initiated()) {
            if (get_ticks() >= get_velocity()) {
                this.Move();
                ClearTicks();
            }
            if (chase) {
                IncreaseWeaponsTicksBy(tick);
                if (is_loaded()) {
                    if (get_weaponTicks() >= _reload) {
                        Shoot();
                        ClearWeaponTicks();
                    }
                } else if (get_weaponTicks() >= _weaponize) {
                    Load();
                    ClearWeaponTicks();
                }
            }
        }
    }

    private void Load() {
        _loaded = true;
    }

    public abstract void Shoot();

    private void IncreaseWeaponsTicksBy(double tick){
        _weaponTicks += tick;
    }

    private void ClearWeaponTicks(){
        _weaponTicks = 0;
    }

    private boolean is_loaded() {
        return _loaded;
    }

    private double get_weaponTicks() {
        return _weaponTicks;
    }
}
