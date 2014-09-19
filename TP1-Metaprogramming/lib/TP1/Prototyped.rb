require "TP1/Singleton_Module_Behaviour"

module TP

  module Prototyped

    def self.included(including_class)
      # Including Singleton_Module clone just not to override its previous definition
      including_class.include (Singleton_Module.clone.include Proto_Singleton_Module)
    end

    def self.extended(extending_object)
      extending_object.extend (Singleton_Module.clone.include Proto_Singleton_Module)
      extending_object.extend extending_object.singleton_module
    end

    def set_method(selector, block)
      self.define_singleton_module_method(selector, block)
    end

    def set_property(selector, value)
      self.instance_module_variable_define(selector)
      self.instance_module_variable_set(selector, value)
    end

    def set_prototype(object)
      self.set_property(:prototype, object)
      self.singleton_module.include self.prototype.singleton_module
      self.extend self.singleton_module
    end

  end

  module Proto_Singleton_Module
    # It's pretty important to define a method_missing behaviour and NOT expect that any behaviour added to singleton module will appear on it's own.
    # Ruby's linearization system makes modules respond only to a PREVIOUSLY DEFINED methods AT INCLUDE TIME. This means that,
    # when behaviour is added to modules, it doesn't impact on any previous instance including them.
    def method_missing(method, *args, &block)
      if (self.singleton_module.method_defined?(method))
        method_to_execute = self.singleton_module.instance_method(method)
        method_to_execute.bind(self).call(*args, &block)
      elsif (method.to_s[-2,2] == "_=")
        raise "es Metodo"
      elsif (method.to_s[-1,1] == "=")
        raise "es Prop"
      else
        super
      end
    end
  end

end

