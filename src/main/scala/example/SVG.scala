package example
import org.scalajs.dom
import org.scalajs.dom.html._
import scalajs.js
import scalajs.js.annotation.JSExport
import js.Dynamic.literal
import com.felstar.scalajs.velocity._

@JSExport
object SVG extends {  
    
  @JSExport
  def main(svg:dom.svg.SVG):Unit = {
        
   def $(sel:String)=dom.document.querySelector(sel)
    
   val circleLeft=$("#circleLeft").asInstanceOf[dom.svg.Circle]
   val circleRight=$("#circleRight").asInstanceOf[dom.svg.Circle]

   val animateOut=(_:dom.raw.Event)=>{
    circleLeft.velocity(literal(cx="-=15px"),easing="spring")
    circleRight.velocity(literal(cx="+=15px"),easing="spring")     
   }
  
   val animateIn=(_:dom.raw.Event)=>{
    circleLeft.velocity(literal(cx="+=15px"),easing="spring")
    circleRight.velocity(literal(cx="-=15px"),easing="spring")     
   }
   
  $("#outButton").asInstanceOf[Button].onclick=animateOut
  $("#inButton").asInstanceOf[Button].onclick=animateIn

  val doge=$("#doge").asInstanceOf[dom.svg.Image]
  val dogeWidth=doge.width.baseVal.value  
  
  val pulse=(ev:dom.raw.Event)=>{
    val circle=ev.currentTarget.asInstanceOf[dom.svg.Circle]
    val cx=circle.cx.baseVal.value
    val cy=circle.cy.baseVal.value
    val r=circle.r.baseVal.value    
    doge.velocity("stop",options=true).velocity(literal(x=cx-dogeWidth/2,y=cy+r),duration=300,easing="spring")
    circle.velocity(literal(r="+=10px", strokeWidth=2,stroke="#000000"),duration=250).velocity(literal(r="-=10px", strokeWidth=0),duration=150)     
   }
  
  circleLeft.onmouseover=pulse
  circleRight.onmouseover=pulse

     // create own Circles in code. Note how they work just fine with Velocity
  val r=40
  val cx=200
  for (i<-1 to 8){
   val circle=org.scalajs.dom.document.createElementNS("http://www.w3.org/2000/svg", "circle").asInstanceOf[dom.svg.Circle]
   circle.setAttribute("r", r+"")
   circle.setAttribute("fill", s"rgb(0,0,${16*i+128})")
   circle.setAttribute("cx",cx+r*i*2+"")
   circle.setAttribute("cy",200+i*r/2+"")   
   svg.appendChild(circle)
   
   circle.onmouseover=pulse
  }
   // puts doge at the top
  svg.appendChild(doge)  
  }
}
