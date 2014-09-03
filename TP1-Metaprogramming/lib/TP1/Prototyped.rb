
module TP

  module Prototyped

    def set_method(selector, block)

    end

    def set_property(selector, value)
      self.singleton_class.class_eval{ attr_accessor selector }
      self.instance_variable_set("@#{selector}", value)
    end

    def set_prototype(object)
      #¿Sera necesario editar las propiedades?
      self.set_property(:prototype, object)
      self.define_singleton_method(:method_missing, lambda{
                                  |name, *args, &block|
                                  self.prototype.send name, *args, &block })
      self.prototype = object
    end

  end

end

