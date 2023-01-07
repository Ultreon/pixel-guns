package com.ultreon.mods.pixelguns.item.gun;

import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;

import java.util.ArrayList;

public class GunHitscanHelper {
    public static HitResult getCollision(ServerWorld world, Entity shooter, int maxDistance) {
        Vec3d origin = shooter.getPos();
        //Vec3d destination = shooter.getEyePos().
        return null;
    }

    public static BlockHitResult getBlockCollision(ServerWorld world, Entity shooter, int maxDistance) {
        return world.raycast(new RaycastContext(shooter.getPos(), shooter.getEyePos().multiply(maxDistance), RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, shooter));
    }
    public static BlockHitResult getEntityCollision(ServerWorld world, Entity shooter, int maxDistance) {
        //ProjectileUtil.raycast()
        return world.raycast(new RaycastContext(shooter.getPos(), shooter.getEyePos().multiply(maxDistance), RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, shooter));
    }

    private static int getMaxDistanceInLoadedChunks() {
        return 0;
    }

    private Point[] useVisionLine(Point origin, Point destination)
    {
        ArrayList<Point> points = new ArrayList<>();
        int ystep, xstep;    // the step on y and x axis
        int error;           // the error accumulated during the increment
        int errorprev;       // *vision the previous value of the error variable
        int y = origin.y, x = origin.x;  // the line points
        double ddy, ddx;        // compulsory variables: the double values of dy and dx
        int dx = destination.x - origin.x;
        int dy = destination.y - origin.y;
        points.add(origin);  // first point
        // NB the last point can't be here, because of its previous points.add(new Point(which has to be verified)
        if (dy < 0) {
            ystep = -1;
            dy = -dy;
        } else
            ystep = 1;
        if (dx < 0) {
            xstep = -1;
            dx = -dx;
        } else
            xstep = 1;
        ddy = 2 * dy;  // work with double values for full precision
        ddx = 2 * dx;
        if (ddx >= ddy) {  // first octant (0 <= slope <= 1)
            // compulsory initialization (even for errorprev, needed when dx==dy)
            errorprev = error = dx;  // start in the middle of the square
            for (int i = 0; i < dx; i++) {  // do not use the first points.add(new Point(already done)
                x += xstep;
                error += ddy;
                if (error > ddx){  // increment y if AFTER the middle ( > )
                    y += ystep;
                    error -= ddx;
                    // three cases (octant == right->right-top for directions below):
                    if (error + errorprev < ddx)  // bottom square also
                        points.add(new Point(x, y-ystep));
                    else if (error + errorprev > ddx)  // left square also
                        points.add(new Point(x-xstep, y));
                    else{  // corner: bottom and left squares also
                        points.add(new Point(x, y-ystep));
                        points.add(new Point(x-xstep, y));
                    }
                }
                points.add(new Point(x, y));
                errorprev = error;
            }
        } else {  // the same as above
            errorprev = error = dy;
            for (int i = 0; i < dy; i++) {
                y += ystep;
                error += ddx;
                if (error > ddy) {
                    x += xstep;
                    error -= ddy;
                    if (error + errorprev < ddy)
                        points.add(new Point(x-xstep, y));
                    else if (error + errorprev > ddy)
                        points.add(new Point(x, y-ystep));
                    else {
                        points.add(new Point(x-xstep, y));
                        points.add(new Point(x, y-ystep));
                    }
                }
                points.add(new Point(x, y));
                errorprev = error;
            }
        }
        // assert ((y == y2) && (x == x2));  // the last points.add(new Point(y2,x2)) has to be the same with the last point of the algorithm
        
        return points.toArray(new Point[0]);
    }
    
    record Point(int x, int y) {}
}
