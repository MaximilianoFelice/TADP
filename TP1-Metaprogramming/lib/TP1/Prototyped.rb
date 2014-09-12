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
      self.define_singleton_module_method(selector, block)
    end

    def set_property(selector, value)
      self.instance_module_variable_define(selector)
      self.instance_variable_set("@#{selector}", value)
    end

    def set_prototype(object)
      self.set_property(:prototype, object)
      self.singleton_module.include self.prototype.singleton_module
    end

  end

end

