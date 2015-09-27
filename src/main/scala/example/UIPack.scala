package example
import org.scalajs.dom
import org.scalajs.dom.html._
import scalajs.js
import scalajs.js.annotation.JSExport
import js.Dynamic.literal
import com.felstar.scalajs.velocity._
import org.scalajs.dom.ext.PimpedNodeList

@JSExport
object UIPack extends {  
    
  @JSExport
  def main():Unit = {
       
   def $(sel:String)=dom.document.querySelector(sel).asInstanceOf[Element]
   def $$(sel:String)=dom.document.querySelectorAll(sel)
   
   val divs=$$("div")
   val title=$("#title")
   
   val Seq(e1,e2,e3,_*)=divs.seq
     // note that we call an existing effect
   val seq=js.Array(        
        literal(e=title,p="fadeIn"),
        literal(e=title,p="callout.shake",options=literal(duration=1500)),
        literal(e=e1,p="callout.bounce"),
        literal(e=e2,p=literal(translateX= "+=100"),options=literal(sequenceQueue=false)),
        literal(e=e3,p=literal(translateY= "+=100"))
       )

       Velocity.RunSequence(seq)
       
       // if you want to do it the hard way. remove title calls from above sequence and RunSequence(seq)
       // then use promises to chain intro fadeIn/callout.shake then RunSequence(seq)            
       // Velocity(title,"fadeIn").andCall(Velocity(title,"callout.shake",literal(duration=1500)).andCall(Velocity.RunSequence(seq)))     
     
     Velocity.RegisterEffect("callout.twirl", literal(
         defaultDuration=3000,
         calls=js.Array(
             js.Array(literal(rotateZ=1080),0.50),
             js.Array(literal(scaleX=0.50),0.25,literal(easing="spring")),
             js.Array(literal(scaleX=1),0.25,literal(easing="spring"))
             ),
           reset=literal(rotateZ=0)
         ))
       
     val lorems=$$("div.lorem")
     
     val effect=$("#dataBody-UIPackSelEffect").asInstanceOf[Select]
     val duration=$("#dataBody-UIPackSelDuration").asInstanceOf[Select]   
     val stagger=$("#dataBody-UIPackSelStagger").asInstanceOf[Select]
     val drag=$("#dataBody-UIPackSelDrag").asInstanceOf[Select]
     val fire=$("#dataBody-UIPackFire").asInstanceOf[Button]
     val fireSelf=$("#dataBody-UIPackFireSelf").asInstanceOf[Button]   
      
     val animate=(el:js.Any)=>if (!effect.value.isEmpty) {
       val eff=effect.value
       if (eff == "fadeIn" || eff.endsWith("In")) {
			  el.velocity(literal(opacity=0), literal(delay=100,display= "block" ));
		   }
       
       el.velocity(eff,literal(duration=duration.value,stagger=stagger.value,
           animateParentHeight= false,drag=drag.value.toBoolean && eff.startsWith("transition"),backwards=eff.endsWith("Out")))
       
       if (eff == "fadeOut" || eff.endsWith("Out")) {
			  el.velocity(literal(opacity=1), literal(display= "block" ));
		   }
     }
   
     val fireAnimation=(_:dom.raw.Event)=>{       
       if (effect.value.isEmpty) dom.alert("Please choose an effect")
       else animate(lorems)          
     }
   
     effect.onchange=fireAnimation     
     duration.onchange=fireAnimation
     stagger.onchange=fireAnimation
     drag.onchange=fireAnimation
     fire.onclick=fireAnimation    
     fireSelf.onclick=(_:dom.raw.Event)=>animate(fireSelf)     
  }
}