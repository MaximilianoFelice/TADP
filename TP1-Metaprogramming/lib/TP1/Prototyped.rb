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
      self.instance_variable_set("@#{selector}", value)
    end

    def set_prototype(object)
      self.set_property(:prototype, object)
      self.singleton_module.singleton_class.include self.prototype.singleton_module
    end

  end

end

