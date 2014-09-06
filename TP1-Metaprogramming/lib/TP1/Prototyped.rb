
module TP

  module Prototyped

    def set_method(selector, block)
      self.define_singleton_method(selector, block)
    end

    def set_property(selector, value)
      self.singleton_class.class_eval{ attr_accessor selector }
      self.instance_variable_set("@#{selector}", value)
    end

    def set_prototype(object)
      #¿Sera necesario editar las propiedades? Claramente no :)
      self.set_property(:prototype, object)
      self.set_method(:method_missing, lambda{
                                  |name, *args, &block|
                                  # TODO: Encontrar un workaround. Obtener el metodo de prototype y luego bindearlo.
                                  self.prototype.send name, *args, &block })
      self.prototype = object
    end

  end

end

