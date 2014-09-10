require "TP1/Singleton_Module_Behaviour"

module TP

  module Prototyped

    def self.included(including_class)
      including_class.include Singleton_Module
    end

    def self.extended(extending_object)
      extending_object.extend Singleton_Module
    end

    def set_method(selector, block)
      self.singleton_module.send(:define_method, selector, block)
    end

    def set_property(selector, value)
      self.singleton_module.module_eval{ attr_accessor selector }
      self.send("#{selector}=", value)
    end

    def set_prototype(object)
      self.set_property(:prototype, object)
      self.set_method(:method_missing, lambda{
                                  |name, *args, &block|
                                  # TODO: Encontrar un workaround. Obtener el metodo de prototype y luego bindearlo.
                                  self.prototype.send name, *args, &block })
      self.prototype = object
    end

  end

end

