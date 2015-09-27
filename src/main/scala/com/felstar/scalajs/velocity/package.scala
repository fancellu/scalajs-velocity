package com.felstar.scalajs

import scalajs.js
import org.scalajs.dom
import org.scalajs.dom._
import js.Dynamic.literal

package object velocity {
  implicit class VelocityElement(el:js.Any){
    def velocity(properties: js.Any, options:js.Any): VelocityElement={
      Velocity(el=el,properties=properties, options=options)
      this
    }
    def velocity(properties: js.Any, duration: js.Any= Velocity.defaults.duration, easing: String=null, complete: js.Function=null,begin: js.Function=null): VelocityElement={
      Velocity(el=el,properties=properties, options=literal(duration=duration, easing=easing, complete=complete,begin=begin))
      this
    }    
  }
  implicit class PromisePimp(promise:Promise){
     // simple callByName so you don't have to declare pointless function/parameter handling
    def andCall[T](onFulfilled: =>T):Promise=promise.andThen(()=>onFulfilled)
  }
}