package example
import org.scalajs.dom
import org.scalajs.dom.html._
import scalajs.js.annotation.JSExport
import scalajs.js
import js.Dynamic.literal
import com.felstar.scalajs.velocity._

class Morph(id:String){
    
  def $(sel:String)=dom.document.querySelector(sel).asInstanceOf[Element]
  
  val div = $(s"#$id .with-velocity")
  val text = $(s"#$id .text")
  val icon = $(s"#$id .icon")
  val play = $(s"#$id .play-velocity")
  
  val reverseAnimation: (dom.MouseEvent => Unit) = e => {
    println("Reversing ");
    Velocity(div, "reverse")
    Velocity(text, "reverse")
    Velocity(icon, "reverse")
   
    div.onclick = playAnimation
    play.onclick = playAnimation
  }

  val playAnimation: (dom.MouseEvent => Unit) = e => {
    println("I have been clicked ");
    Velocity(div, literal(
      borderRadius = "25px",
      width = "45px",
      paddingLeft = 0,
      paddingRight = 0,
      backgroundColor = "#8CC152",
      color = "#fff",
      borderColor = "#8CC152",
      boxShadowX = 0,
      boxShadowY = 0), duration = 340, easing = "easeInQuad")

    Velocity(text, literal(
      scale = 0, opacity = 0), duration = 150, easing = "easeInQuad")

    Velocity(icon, literal(opacity = 1), duration = 350, easing = "easeInQuad")

    div.onclick = reverseAnimation
    play.onclick = reverseAnimation
  }
  
  div.onclick = playAnimation
  play.onclick = playAnimation
}

@JSExport
object Morph extends {
 
  @JSExport
  def main(id:String): Unit = {
    new Morph(id)    
  }
}