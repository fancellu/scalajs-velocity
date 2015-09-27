package example
import org.scalajs.dom
import org.scalajs.dom.html._
import scalajs.js
import scalajs.js.annotation.JSExport
import js.Dynamic.literal
import com.felstar.scalajs.velocity._

@JSExport
object Vel1 extends {  
    
  @JSExport
  def main(el:dom.html.Element):Unit = {
    
     // Make animations instant
     // Velocity.mock=true
     // Make animations slower by 3
     // Velocity.mock=3
    
   def $(sel:String)=dom.document.querySelector(sel).asInstanceOf[Element]
   def $$(sel:String)=dom.document.querySelectorAll(sel)
   
   el.onclick= (event:dom.MouseEvent)=> {
     val me=event.currentTarget
     me.velocity(literal(translateX="+=40px"),duration=1000)
      // kick off vertical slide in 500ms, run at same time as above, due to queue=false. Ensure you don't close over event.currentTarget inside timeout    

      // with dom.setTimeout
     //dom.setTimeout(()=>Velocity(me,literal(top="+=40px"),options=literal(duration=1000,queue=false)), 500)
      // with delay option
     Velocity(me,literal(translateY="+=40px"),options=literal(duration=1000,queue=false,delay=500))
   }
   
     // on elements. Note begin and complete is passed array of elements effected
   el.velocity(literal(translateX="200px"),duration=1500,easing="500,20",complete={()=>println("first div springs into place")},
       begin={(elements:js.Array[dom.html.Element])=>println("about to start "+elements.map(_.id))})
     // on multiple divs
   val divs=$$("div.box")
   divs.velocity(literal(translateX="450px"),duration=3000,easing="ease",complete={()=>println("all divs ease into place")})
   
   val mydiv2=$("#mydiv2")
       // via static utility function
   Velocity(mydiv2,literal(translateX="50px",rotateY=360),
       literal(duration=1000,easing="linear",complete={()=>println("second div slides into place")},begin={()=>println("about to end!")}))
   Velocity(mydiv2,"reverse",duration=500,complete={()=>println("second div reverses previous move")})

   // via single object static. Doesn't work for some reason
   //   Velocity(mydiv2,literal(
//       properties=literal(opacity=0.5),
//       options=literal(duration=500)
//       ))

      // note width has own easing, which is a spring, defined as an array
     Velocity(mydiv2,literal(opacity=0.5, width=js.Array(400,js.Array( 250, 15 ))),literal(duration=500,begin=()=>println(" and fade")))
     // example of custom easing registration. Takes 0-1, returns 0-1
     
     Velocity.Easings.myCustomEasing= (p:Float)=>0.5 - Math.cos( p * Math.PI ) / 2
     el.velocity(literal(translateX="200px"),duration=1500,easing="myCustomEasing")

     mydiv2.onclick= (event:dom.MouseEvent)=>  Velocity(event.currentTarget,"reverse")
     
     val mydiv3=$("#mydiv3")
     
     // example of a progress callback, along with optional tweenValue
     // uses loop to make it loop back to original position, x3, to give a shake effect
     mydiv3.onclick= (event:dom.MouseEvent)=>  {       
       Velocity(event.currentTarget,literal(translateX="-=30px",tween=1000),options=literal(
       duration=50,           
       loop=3,
       progress=(elements:js.Array[dom.html.Element],complete:Double,remaining:Int,start:Double,tweenValue:Double)=>
        println(elements.map(_.id)+" complete="+complete*100+"% remaining="+remaining+"ms Tween="+tweenValue+" Start="+start)
       ))
     }
       //examles of commands, with options, chained   
     val mydiv4=$("#mydiv4")
     mydiv4.velocity("fadeIn",literal(duration=1500)).velocity("fadeOut",literal(delay=500,duration=1500,display="auto"))
     
        // use of hook 
     val mydiv5=$("#mydiv5")
     Velocity.hook(mydiv5,"translateX","125px")
     Velocity.hook(mydiv5,"boxShadowBlur","10px")
     println(Velocity.hook(mydiv5,"translateX"))
     
      // promises, I installed bluebird so that Promises would work for Safari
      // note I use andThen/andCatch, as Scala reserves then/catch keywords
     val mydiv6=$("#mydiv6")
     Velocity(mydiv6,literal(translateY="+=40px"),options=literal(duration=1000,delay=500))
      .andThen(()=>println("  Promise finished"))
    
     Velocity(mydiv6,"silly command")
      .andCatch((error:js.Any)=>println("  Promise failed "+error))  
      
       // value function, i is index into the divs, so each one gets offset differently
      val lorems=$$("div.lorem")
      Velocity(lorems,literal(translateX=(i:Int,total:Int)=>i*40),literal(delay=500))
  }
}