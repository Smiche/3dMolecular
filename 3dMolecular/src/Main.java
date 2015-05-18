import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.geometry.Primitive;
import javax.media.j3d.Appearance;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Material;
import javax.media.j3d.PointLight;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.Alpha;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Node;
import javax.vecmath.Vector3f;
import javax.vecmath.Point3f;
import javax.vecmath.Point3d;
import javax.vecmath.Color3f;
import java.awt.Frame;
import java.awt.Button;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main {

	public Main() {

		SimpleUniverse universe = new SimpleUniverse();

		BranchGroup group = new BranchGroup();
		
	      Material whiteSphMaterial = new Material();
	      whiteSphMaterial.setDiffuseColor(1.0f,1.0f,1.0f);
	      Appearance whiteSphAppearance = new Appearance();
	      whiteSphAppearance.setMaterial(whiteSphMaterial);
	      
	      Material redSphMaterial = new Material();
	     redSphMaterial.setDiffuseColor(1.0f,0.0f,0.0f);
	      Appearance redSphAppearance = new Appearance();
	      redSphAppearance.setMaterial(redSphMaterial);
	      
	      
	     /* for (float x = 0.4f; x <= 1.0f; x = x + 0.2f)

	      {

		      Sphere sphere3 = new Sphere(0.09f,
		                Primitive.GENERATE_NORMALS,
		                30,
		                whiteSphAppearance);

		      TransformGroup tg3 = new TransformGroup();

		      Transform3D transform3 = new Transform3D();

		      TransformGroup whiteTransXformGroup2 = translate(
	                 sphere3,
	                  new Vector3f(x,0.0f,0.8f));
		      TransformGroup whiteRotXformGroup2 = 
		               rotate(whiteTransXformGroup2,new Alpha(-1,300));


		      tg3.setTransform(transform3);

		      tg3.addChild(whiteRotXformGroup2);
		      
		      group.addChild(tg3);

	      } */
	      
	      Sphere sphere3 = new Sphere(0.09f,
	                Primitive.GENERATE_NORMALS,
	                30,
	                redSphAppearance);

	      TransformGroup tg3 = new TransformGroup();

	      Transform3D transform3 = new Transform3D();

	      TransformGroup whiteTransXformGroup2 = translate(
                 sphere3,
                  new Vector3f(0.20f,0.0f,0.7f));
	      TransformGroup whiteRotXformGroup2 = 
	               rotate(whiteTransXformGroup2,new Alpha(-1,5000));


	      tg3.setTransform(transform3);

	      tg3.addChild(whiteRotXformGroup2);

	      group.addChild(tg3);
	      
		Sphere electron = new Sphere(0.09f,
                Primitive.GENERATE_NORMALS,
                30,
                whiteSphAppearance);
		Sphere sphere2 = new Sphere(0.12f,
                Primitive.GENERATE_NORMALS,
                30,
                redSphAppearance);

		TransformGroup tg = new TransformGroup();
		
		TransformGroup tg2 = new TransformGroup();
		
		Transform3D mid = new Transform3D();
		
		
		
		mid.setTranslation(new Vector3f(0f,0f,0f));
		tg2.addChild(sphere2);
		tg2.setTransform(mid);
		
		Transform3D transform = new Transform3D();

		Vector3f vector2 = new Vector3f(.0f, .0f, .0f);

		transform.setTranslation(vector2);
		
	       TransformGroup whiteTransXformGroup = translate(
                   electron,
                   new Vector3f(0.0f,0.0f,0.8f));

//Begin rotation of translated white sphere around
// the vertical axis at the origin in 3D space.
	       TransformGroup whiteRotXformGroup = 
	               rotate(whiteTransXformGroup,new Alpha(-1,5000));


		tg.setTransform(transform);

		tg.addChild(whiteRotXformGroup);

		group.addChild(tg2);
		group.addChild(tg);

		
		
		Color3f light1Color = new Color3f(1.0f,1.0f,1.0f); // green light

		
		BoundingSphere bounds =

		new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);

		Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);

		DirectionalLight light1

		= new DirectionalLight(light1Color, light1Direction);

		light1.setInfluencingBounds(bounds);

		group.addChild(light1);

		universe.getViewingPlatform().setNominalViewingTransform();

		// add the group of objects to the Universe

		
		universe.addBranchGraph(group);

	}

	public static void main(String[] args) {

		new Main();

	}
    TransformGroup translate(Node node,Vector3f vector){

        Transform3D transform3D = new Transform3D();
        transform3D.setTranslation(vector);
        TransformGroup transformGroup = 
                                     new TransformGroup();
        transformGroup.setTransform(transform3D);

        transformGroup.addChild(node);
        return transformGroup;
    }
    TransformGroup rotate(Node node,Alpha alpha){

        TransformGroup xformGroup = new TransformGroup();
        xformGroup.setCapability(
                      TransformGroup.ALLOW_TRANSFORM_WRITE);

        //Create an interpolator for rotating the node.
        RotationInterpolator interpolator = 
                 new RotationInterpolator(alpha,xformGroup);

        //Establish the animation region for this
        // interpolator.
        interpolator.setSchedulingBounds(new BoundingSphere(
                             new Point3d(0.0,0.0,0.0),1.0));

        //Populate the xform group.
        xformGroup.addChild(interpolator);
        xformGroup.addChild(node);

        return xformGroup;

      }

}